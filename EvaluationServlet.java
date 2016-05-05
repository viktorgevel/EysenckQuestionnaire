import java.io.IOException;
import javax.servlet.http.*;

/**
 * Created by Family on 04.05.2016.
 */
public class EvaluationServlet extends HttpServlet {

    static final String STANDARD_RESPONSE = "<!DOCTYPE html><html>" +
            "<head><meta charset=\"UTF-8\">" +
            "<title>EysenckResults</title></head>" +
            "<body><h2>%s</h2></body></html>";

    int pointExtraversion; int pointNeuroticism; int pointLie;
    String resExtraversion; String resNeuroticism; String resLie;
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        //Блок полученных ответов. Китайский код
        String q1 = req.getParameter("q1");
        String q2 = req.getParameter("q2");
        String q3 = req.getParameter("q3");
        String q4 = req.getParameter("q4");
        String q5 = req.getParameter("q5");
        String q6 = req.getParameter("q6");
        String q7 = req.getParameter("q7");
        String q8 = req.getParameter("q8");
        String q9 = req.getParameter("q9");
        String q10 = req.getParameter("q10");
        String q11 = req.getParameter("q11");
        String q12 = req.getParameter("q12");
        String q13 = req.getParameter("q13");
        String q14 = req.getParameter("q14");
        String q15 = req.getParameter("q15");
        String q16 = req.getParameter("q16");
        String q17 = req.getParameter("q17");
        String q18 = req.getParameter("q18");
        String q19 = req.getParameter("q19");
        String q20 = req.getParameter("q20");
        String q21 = req.getParameter("q21");
        String q22 = req.getParameter("q22");
        String q23 = req.getParameter("q23");
        String q24 = req.getParameter("q24");
        String q25 = req.getParameter("q25");
        String q26 = req.getParameter("q26");
        String q27 = req.getParameter("q27");
        String q28 = req.getParameter("q28");
        String q29 = req.getParameter("q29");
        String q30 = req.getParameter("q30");
        String q31 = req.getParameter("q31");
        String q32 = req.getParameter("q32");
        String q33 = req.getParameter("q33");
        String q34 = req.getParameter("q34");
        String q35 = req.getParameter("q35");
        String q36 = req.getParameter("q36");
        String q37 = req.getParameter("q37");
        String q38 = req.getParameter("q38");
        String q39 = req.getParameter("q39");
        String q40 = req.getParameter("q40");
        String q41 = req.getParameter("q41");
        String q42 = req.getParameter("q42");
        String q43 = req.getParameter("q43");
        String q44 = req.getParameter("q44");
        String q45 = req.getParameter("q45");
        String q46 = req.getParameter("q46");
        String q47 = req.getParameter("q47");
        String q48 = req.getParameter("q48");
        String q49 = req.getParameter("q49");
        String q50 = req.getParameter("q50");
        String q51 = req.getParameter("q51");
        String q52 = req.getParameter("q52");
        String q53 = req.getParameter("q53");
        String q54 = req.getParameter("q54");
        String q55 = req.getParameter("q55");
        String q56 = req.getParameter("q56");
        String q57 = req.getParameter("q57");

        String[] extraversionTrue = {q1, q3, q8, q10, q13, q17, q22, q25, q27, q39, q44, q46, q49, q53, q56};
        String[] extraversionFalse = {q5, q15, q20, q29, q32, q34, q37, q41, q51};
        String[] neuroticism = {q2, q4, q7, q9, q11, q14, q16, q19, q21, q23, q26, q28, q31, q33, q35, q38, q40, q43,
                q45, q47, q50, q52, q55, q57};
        String[] lieTrue = {q6, q24, q36};
        String[] lieFalse = {q12, q18, q30, q42, q48, q54};

        if (req.getContentLength() < 389) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(String.format(STANDARD_RESPONSE, "<p>Вы не ответили на все вопросы, попробуйте снова.<p>"));
        } else {

            for (String i : extraversionTrue) {
                if ("yes".equals(i)) {
                    pointExtraversion++;
                }
            }
            for (String i : extraversionFalse) {
                if ("no".equals(i)) {
                    pointExtraversion++;
                }
            }
            for (String i : neuroticism) {
                if ("yes".equals(i)) {
                    pointNeuroticism++;
                }
            }
            for (String i : lieTrue) {
                if ("yes".equals(i)) {
                    pointLie++;
                }
            }
            for (String i : lieFalse) {
                if ("no".equals(i)) {
                    pointLie++;
                }
            }

            if (pointExtraversion > 19) {
                resExtraversion = "Вы яркий экстраверт. ";
                StatisticServlet.countOfDeepExtraverts++;
            } else if (pointExtraversion > 15) {
                resExtraversion = "Вы экстраверт. ";
                StatisticServlet.countOfExtraverts++;
            } else if (pointExtraversion > 12) {
                resExtraversion = "Вы не являетесь выраженным интровертом или экстравертом. ";
                StatisticServlet.countOfMiddleman++;
            } else if (pointExtraversion > 9) {
                resExtraversion = "Вы интроверт. ";
                StatisticServlet.countOfIntroverts++;
            } else {
                resExtraversion = "Вы глубокий интроверт. ";
                StatisticServlet.countOfDeepIntroverts++;
            }

            if (pointNeuroticism > 19) {
                resNeuroticism = "У Вас очень высокий уровень нейротизма. ";
                StatisticServlet.countOfDeepUnStable++;
            } else if (pointNeuroticism > 14) {
                resNeuroticism = "У Вас высокий уровень нейротизма. ";
                StatisticServlet.countOfUnStable++;
            } else if (pointNeuroticism > 7) {
                resNeuroticism = "У Вас средний уровень нейротизма. ";
                StatisticServlet.countOfStable++;
            } else {
                resNeuroticism = "У Вас низкий уровень нейротизма. ";
                StatisticServlet.countOfDeepStable++;
            }

            if (pointLie > 4) {
                resLie = "Но Вы были неискренни в ответах. Возможно Вам свойственна некоторая демонстративность " +
                        "поведения и ориентированность на социальное одобрение.";
                StatisticServlet.countOfFalse++;
            } else {
                resLie = "Скорее всего Вы были искренними в своих ответах.";
                StatisticServlet.countOfTrue++;
            }

            String res = "<p>" + resExtraversion + resNeuroticism + resLie + "</p>";

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(String.format(STANDARD_RESPONSE, res));
        }
    }


}