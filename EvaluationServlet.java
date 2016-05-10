import java.io.IOException;
import java.util.*;
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

        List<String> q = new ArrayList<>();
        for(int i = 0; i < 57; i++) {
            q.add(req.getParameter("q"+(i+1)));
        }

        int[] extraversionTrue = {1, 3, 8, 10, 13, 17, 22, 25, 27, 39, 44, 46, 49, 53, 56};
        int[] extraversionFalse = {5, 15, 20, 29, 32, 34, 37, 41, 51};
        int[] neuroticism = {2, 4, 7, 9, 11, 14, 16, 19, 21, 23, 26, 28, 31, 33, 35, 38, 40, 43,
                45, 47, 50, 52, 55, 57};
        int[] lieTrue = {6, 24, 36};
        int[] lieFalse = {12, 18, 30, 42, 48, 54};

        if (req.getContentLength() < 389) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(String.format(STANDARD_RESPONSE, "<p>Вы не ответили на все вопросы, попробуйте снова.<p>"));
        } else {

            for (int i : extraversionTrue) {
                if ("yes".equals(q.get(i - 1))) {
                    pointExtraversion++;
                }
            }
            for (int i : extraversionFalse) {
                if ("no".equals(q.get(i - 1))) {
                    pointExtraversion++;
                }
            }
            for (int i : neuroticism) {
                if ("yes".equals(q.get(i - 1))) {
                    pointNeuroticism++;
                }
            }
            for (int i : lieTrue) {
                if ("yes".equals(q.get(i - 1))) {
                    pointLie++;
                }
            }
            for (int i : lieFalse) {
                if ("no".equals(q.get(i - 1))) {
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