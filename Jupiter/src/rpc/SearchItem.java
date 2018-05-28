package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//return a response
//		PrintWriter out = response.getWriter();
//		if(request.getParameter("username") != null) {
//			String username = request.getParameter("username");
//			out.print("Hello" + username);
//		}
//		out.close();
		/*return a html page
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>This is a html page</h1>");
		out.println("</body></html>");
		out.close();
		*/
		/*return a JSON object
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String username = "";
		if(request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("username", username);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(obj);
		out.close();
		*/
		/*return a list of json array
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		try {
			array.put(new JSONObject().put("username","abcd"));
			array.put(new JSONObject().put("username", "1234"));
		}catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(array);
		out.close();
		*/
		//use helper function
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		String term = request.getParameter("term");
		
		DBConnection connection = DBConnectionFactory.getConnection();
		List<Item> items = connection.searchitems(lat, lon, term);
		connection.close();
		
		List<JSONObject> list = new ArrayList<>();
		//JSONArray array = new JSONArray();
		try {
//			array.put(new JSONObject().put("username", "abcd"));
//			array.put(new JSONObject().put("username", "1234"));
			
			/*
			TicketMasterAPI tmAPI = new TicketMasterAPI();
			List<Item> items = tmAPI.search(lat, lon, keyword);
			
			for(Item item : items) {
				JSONObject obj = item.toJSONObject();
				array.put(obj);
			}
			*/
			for(Item item : items) {
				//add a thin version of item object
				JSONObject obj = item.toJSONObject();
				list.add(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray(list);
		RpcHelper.writeJsonArray(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
