package com.stanfy.helium.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class DataRetrieveServlet extends HttpServlet {

  private static final int INITIAL_CAPACITY = 500;
  private static final String JSON_CONTENT_TYPE = "application/json";
  private static final String PATH_DATA = "/data/";
  private static final HashMap<String, Data> STORE = new HashMap<>(INITIAL_CAPACITY);
  private static final Gson GSON = new GsonBuilder().serializeNulls().create();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    setContentType(resp);

    String dataId = getDataId(req);
    if (dataId != null) {
      Data data = STORE.get(dataId);
      String jsonData = buildResponse(data);
      putClientResponse(resp, jsonData);
    } else {
      setErrorStatusCode(resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    setContentType(resp);

    String dataId = getDataId(req);
    if (dataId != null) {
      Data requestData = GSON.fromJson(req.getReader(), Data.class);
      STORE.put(dataId, requestData);
      String jsonData = GSON.toJson(requestData);
      putClientResponse(resp, jsonData);
    } else {
      setErrorStatusCode(resp);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    setContentType(resp);

    String dataId = getDataId(req);
    if (dataId != null) {
      STORE.remove(dataId);
    } else {
      setErrorStatusCode(resp);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    setContentType(resp);

    String dataId = getDataId(req);
    if (dataId != null) {
      Data requestData = GSON.fromJson(req.getReader(), Data.class);
      STORE.put(dataId, requestData);
    } else {
      setErrorStatusCode(resp);
    }
  }

  private String buildResponse(final Data data) {
    ResponseData response = new ResponseData();
    response.setData(data);
    return GSON.toJson(response);
  }

  private void putClientResponse(HttpServletResponse resp, String jsonData) throws IOException {
    PrintWriter respWriter = resp.getWriter();
    respWriter.print(jsonData);
    respWriter.flush();
  }

  private String getDataId(HttpServletRequest req) {
    String path = req.getRequestURI();

    if (path == null || path.isEmpty()) {
      return null;
    }

    String dataId = null;
    if (path.startsWith(PATH_DATA)) {
      dataId = path.replaceFirst(PATH_DATA, "");
      // Check that requests in form "http://host/data/@id" have @id.
      if (dataId.isEmpty()) {
        return null;
      }
    }

    return dataId;
  }

  private void setContentType(HttpServletResponse resp) {
    resp.setContentType(JSON_CONTENT_TYPE);
  }

  private void setErrorStatusCode(HttpServletResponse resp) {
    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }
}
