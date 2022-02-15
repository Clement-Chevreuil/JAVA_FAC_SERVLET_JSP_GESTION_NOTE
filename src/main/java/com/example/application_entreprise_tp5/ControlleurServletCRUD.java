package com.example.application_entreprise_tp5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.application_entreprise_tp5.Cours;
import com.example.application_entreprise_tp5.DAOCours;

@WebServlet("/")
public class ControlleurServletCRUD extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOCours dao;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        dao = new DAOCours(jdbcURL, jdbcUsername, jdbcPassword);

    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCours(request, response);
                    break;
                case "/delete":
                    deleteCours(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCours(request, response);
                    break;
                case "/list":
                    listCours(request, response);
                    break;
                default:
                    listCours(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cours> listCours = dao.listAllCours();
        request.setAttribute("listCours", listCours);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListeCoursCRUD.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("FormulaireCoursCRUD.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cours existingCours = dao.getCours(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FormulaireCoursCRUD.jsp");
        request.setAttribute("cours", existingCours);
        dispatcher.forward(request, response);

    }

    private void insertCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String intitule = request.getParameter("intitule");
        int coef = Integer.parseInt(request.getParameter("coef"));
        int duree = Integer.parseInt(request.getParameter("duree"));

        Cours newCours = new Cours(intitule, coef, duree);
        dao.insertCours(newCours);
        response.sendRedirect("list");
    }

    private void updateCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String intitule = request.getParameter("intitule");
        int coef = Integer.parseInt(request.getParameter("coef"));
        int duree = Integer.parseInt(request.getParameter("duree"));

        Cours cours = new Cours(id, intitule, coef, duree);
        dao.updateBook(cours);
        response.sendRedirect("list");
    }

    private void deleteCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Cours cours = new Cours(id);
        dao.deleteCours(cours);
        response.sendRedirect("list");

    }


}
