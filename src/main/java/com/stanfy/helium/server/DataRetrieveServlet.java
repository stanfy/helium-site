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

  private static final HashMap<String, Data> STORE = new HashMap<>(500);
  private static final Gson GSON = new GsonBuilder().serializeNulls().create();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String dataId = getDataId(req);

    Data data = STORE.get(dataId);
    String jsonData = buildResponse(data);

    putClientResponse(resp, jsonData);
    respondSuccess(resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Data requestData = GSON.fromJson(req.getReader(), Data.class);
    String dataId = getDataId(req);

    STORE.put(dataId, requestData);
    String jsonData = GSON.toJson(requestData);

    putClientResponse(resp, jsonData);
    respondSuccess(resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String dataId = getDataId(req);
    STORE.remove(dataId);

    respondSuccess(resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Data requestData = GSON.fromJson(req.getReader(), Data.class);

    String dataId = getDataId(req);

    STORE.put(dataId, requestData);
    respondSuccess(resp);
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
    return req.getPathInfo().replaceFirst("/", "");
  }

  private void respondSuccess(HttpServletResponse resp) {
    resp.setContentType("application/json");
    resp.setStatus(HttpServletResponse.SC_OK);
  }
}
