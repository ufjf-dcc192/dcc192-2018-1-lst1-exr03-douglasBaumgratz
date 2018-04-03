/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author douglas
 */
@WebServlet(urlPatterns = {"/CalcularValorFinal.html"})
public class TabelaValores extends HttpServlet {

    double valor = 1000;
    double rendimento;
    double ano = valor * (1 + 12 * 0.01);
    int numeroMeses;
    double taxaJuros;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametroMeses = request.getParameter("meses");
        String parametroTaxa = request.getParameter("taxa");

        if (!"".equals(parametroMeses) && parametroMeses != null) {
            numeroMeses = Integer.parseInt(parametroMeses);
        } else {
            numeroMeses = 12;
        }
        if (!"".equals(parametroTaxa) && parametroMeses != null) {
            taxaJuros = Double.parseDouble(parametroTaxa) / 100;
        } else {
            taxaJuros = 0.01;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcularValorFinal</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form>");
            out.println("<label> Meses: <input name = 'meses'/></label>>");
            out.println("<label> Taxa de Juros: <input name = 'taxa'/></label>>");
            out.println("<input type='submit' />");
            out.println("<input type='reset' />");
            out.println("</form>");
            out.println("<br>");

            out.println("<p> Tabela de Valores </p>");
            out.println("<table border = '1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th> Mês </th>");
            out.println("<th>" + ((taxaJuros * 100) - 0.5) + "% </th>");
            out.println("<th>" + ((taxaJuros * 100)) + "% </th>");
            out.println("<th>" + ((taxaJuros * 100) + 0.5) + "% </th>");
            out.println("</tr>");
            out.println("</thead>");

            for (int i = 1; i <= numeroMeses; i++) {
                out.println("<tr>");
                out.println("<td>" + i + "</td>");
                out.println("<td>" + (valor * (1 + (i * (taxaJuros * 0.5)))) + "</td>");
                out.println("<td>" + (valor * (1 + (i * taxaJuros))) + "</td>");
                out.println("<td>" + (valor * (1 + (i * (taxaJuros * 1.5)))) + "</td>");
                out.println("</tr>");

            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
