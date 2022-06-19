package com.google.sps.servlets.FormHandlerServlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import com.google.sps.data.FormData;
import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/load-data")
public class LoadDataServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
      Query<Entity> query =
          Query.newEntityQueryBuilder().setKind("Form").setOrderBy(OrderBy.desc("timestamp")).build();
      QueryResults<Entity> results = datastore.run(query);
  
      List<FormData> forms = new ArrayList<>();
      while (results.hasNext()) {
        Entity entity = results.next();
  
        long id = entity.getKey().getId();
        String name = entity.getString("name");
        String email = entity.getString("email");
        String message = entity.getString("message");
        long timestamp = entity.getLong("timestamp");
  
        FormData form = new FormData(name, email, message, timestamp);
        forms.add(form);
      }
  
      String formsJson = convertToJsonByGson(forms);
  
      response.setContentType("application/json;");
      response.getWriter().println(formsJson);
      
    }

    /**
     * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
     * the Gson library dependency to pom.xml.
     */
    public String convertToJsonByGson(List<FormData> forms){
        return new Gson().toJson(forms);
    }

}
