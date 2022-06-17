package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.FactsData;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Handles requests sent to the /facts URL. Try running a server and navigating to /hello! */
@WebServlet("/facts")
public class FactsServlet extends HttpServlet {


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    //generate the array list
    final ArrayList<FactsData> facts = new ArrayList<FactsData>();
    facts.add(new FactsData("All species of beetles are edible."));
    facts.add(new FactsData("Forest fires move faster uphill than downhill."));
    facts.add(new FactsData("Oak trees do not have acorns until they are fifty years old or older."));
    facts.add(new FactsData("It costs about 3 cents to make a $1 bill in the United States."));
    
    //convert the arrayList of string into json
    String json = convertToJsonByGson(facts);
    

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);

    //for test try/catch exception capture
    //throw new IOException();

  }

  /**
   * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  public String convertToJsonByGson(ArrayList<FactsData> facts){
    return new Gson().toJson(facts);
  }
  
}
