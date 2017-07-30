package com.husrev.satellites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.google.common.io.Files;
import java.nio.charset.Charset;
import com.google.gson.Gson;
import com.husrev.satellites.PVT;
import com.husrev.satellites.Positions;

/**
 * Root resource (exposed at "/" path)
 */
@Path("/")
public class Resources {

  
	
    static List<Positions> positions; //All positions
    static boolean launch = true;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String  Index() throws IOException {
  	
    	if(launch)
    		getPositionsFromFile();
		
		
		
		String html="";
		
		html = readFile("C:/Users/husre/workspace/Satellites-Visualization/html/head.html",Charset.defaultCharset());

		for(Positions pos : positions)
			html += "<p hidden class='sat' id='"+pos.getName() + "' "+
					"x='"+pos.getPvt().get(0).getX() + "' "+
					"y='"+pos.getPvt().get(0).getY() + "' "+
					"z='"+pos.getPvt().get(0).getZ() + "' "+
					"v='"+pos.getPvt().get(0).getTime() + "' "+
					"t='"+pos.getPvt().get(0).getVelocity() + "'> </p>";
	
		
		
		html += readFile("C:/Users/husre/workspace/Satellites-Visualization/html/foot.html",Charset.defaultCharset());
		
      return html;
    }
    

    
    private void getPositionsFromFile() {
    	launch = false; 
    	
		SparkSession spark = SparkSession
			  .builder()
			  .appName("Java Spark SQL basic example")
			  .master("local")
			  .getOrCreate();
	
	Dataset<Row> df = spark.read().json("C:/Users/husre/Desktop/positions.json").toDF();
	Dataset<Row> parsed = df.select(df.col("name") , org.apache.spark.sql.functions.explode(df.col("pvt")).as("p"));
	
	positions = new ArrayList<Positions>();
	

	List<Row> name = parsed.select("name").collectAsList();
	List<Row> time =  parsed.select("p.time").collectAsList();
	List<Row> x =  parsed.select("p.x").collectAsList();
	List<Row> y =  parsed.select("p.y").collectAsList();
	List<Row> z =  parsed.select("p.z").collectAsList();
	List<Row> velocity =  parsed.select("p.velocity").collectAsList();

	
	
	
	
	Positions p = new Positions();
	
	 //For init get first sat
	String satName = name.get(0).toString().replaceAll("[\\[\\]]","");
	p.setName(satName);
	
	//parse
	int i=0;
	for(Row n : name)
	{
		satName = n.toString().replaceAll("[\\[\\]]","");
		
		if(!satName.equals(p.getName()))
		{
			positions.add(p);
			p = new Positions();
			p.setName(satName);
		}
		
		p.getPvt().add(new PVT(Double.parseDouble(x.get(i).toString().replaceAll("[\\[\\]]","")),
				Double.parseDouble(y.get(i).toString().replaceAll("[\\[\\]]","")),
				Double.parseDouble(z.get(i).toString().replaceAll("[\\[\\]]","")),
				Double.parseDouble(velocity.get(i).toString().replaceAll("[\\[\\]]","")),
				time.get(i++).toString().replaceAll("[\\[\\]]","")
				));
	}
	positions.add(p);
	}



	// AJAX Method
    @POST
    @Path("/getPositions")
    public Response getPositions(String seq) throws Exception{
        int seqNo = Integer.parseInt((seq.split("="))[1]); // seq=1 olarak geliyor
        
        
        Gson json = new Gson();
        List<Positions> ajaxPos = new ArrayList<Positions>();
        
        
    	for(Positions p : positions)
    	{
    		Positions pos = new Positions(p.getName());
    		pos.getPvt().add(new PVT(p.getPvt().get(seqNo).getX(),
    				p.getPvt().get(seqNo).getY(),
    				p.getPvt().get(seqNo).getZ(),
    				p.getPvt().get(seqNo).getVelocity(),
    				p.getPvt().get(seqNo).getTime()
    				    				));
    		
    		ajaxPos.add(pos);
    	}
        
        return Response.status(200).entity(json.toJson(ajaxPos)).build();
    }
    
    
    String readFile(String path, Charset encoding) throws IOException {
    				return Files.toString(new File(path), encoding);
    		}
    
    
}
