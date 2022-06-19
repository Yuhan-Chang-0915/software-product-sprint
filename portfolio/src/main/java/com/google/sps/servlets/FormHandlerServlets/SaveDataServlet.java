package com.google.sps.servlets.FormHandlerServlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import org.jsoup.Jsoup;


@WebServlet("/save-data")
public class SaveDataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String name =request.getParameter("name");
    String email = request.getParameter("email");
    String message = request.getParameter("message");
    long timestamp = System.currentTimeMillis();
    // dataStore is the instance of the DataStore class.
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Form");
    FullEntity formEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("email", email)
            .set("message", message)
            .set("timestamp", timestamp)
            .build();
    datastore.put(formEntity);

    response.sendRedirect("/index.html");
  }
}
