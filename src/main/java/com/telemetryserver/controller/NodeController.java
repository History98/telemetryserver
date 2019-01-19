/*
   Author: KMN
   Instituition: LJMU
   Descr: Basic Node Controller
 */


package com.telemetryserver.controller;

import com.telemetryserver.dao.*;
import com.telemetryserver.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class NodeController extends HttpServlet
{
    @Override
    public void init() throws ServletException
    {
        super.init();
        getServletContext().setAttribute("categoryList", NetworkTopology.typeCategoryList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String base = "/jsp/";
        String url = base + "home.jsp";

        String action = req.getParameter("action");
        String category = req.getParameter("category");

        if(action != null)
        {
            switch(action)
            {
                case "allNodes":
                    findAllNodes(req, resp);
                    url = base + "listOfNodes.jsp";
                    break;

                case "category":
                    findAllNodes(req, resp);
                    url = base + "category.jsp?category=" + category;
                    break;

            }
        }

        //Get the request dispacher and forward the page to the URL "url"
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(req, resp);
    }


    private void findAllNodes(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            List<Node> nodes = new NodeDAOImpl().getAllNodes();
            req.setAttribute("nodeList", nodes);
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
    }
}
