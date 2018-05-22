package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		JSONArray array = new JSONArray();
		try {
//			array.put(new JSONObject().put("username", "abcd"));
//			array.put(new JSONObject().put("username", "1234"));
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			String keyword = request.getParameter("term");
			
			TicketMasterAPI tmAPI = new TicketMasterAPI();
			List<Item> items = tmAPI.search(lat, lon, keyword);
			
			for(Item item : items) {
				JSONObject obj = item.toJSONObject();
				array.put(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
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
