import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Family on 05.05.2016.
 */
public class StatisticServlet extends HttpServlet {
    static int countOfDeepExtraverts;
    static int countOfExtraverts;
    static int countOfMiddleman;
    static int countOfDeepIntroverts;
    static int countOfIntroverts;
    static int countOfStable;
    static int countOfDeepStable;
    static int countOfDeepUnStable;
    static int countOfUnStable;
    static int countOfTrue;
    static int countOfFalse;

    static final String STANDARD_RESPONSE = "<!DOCTYPE html><html>" +
            "<head><meta charset=\"UTF-8\">" +
            "<title>EysenckResults</title></head>" +
            "<body><h3>%s</h3></body></html>";

    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String statisticMessage = "<p>Яркие экстраверты - " + countOfDeepExtraverts + "<Br>Экстраверты - "+
                countOfExtraverts + "<Br>Средний тип - " + countOfMiddleman + "<Br>Интроверты - " + countOfIntroverts +
                "<Br>Глубокие интроверты - " + countOfDeepIntroverts + "</p><p>Очень высокий уровень эмоциональной "+
                "неустойчивости - " + countOfDeepUnStable + "<Br>Высокий уровень эмоциональной неустойчивости - "+
                countOfUnStable + "<Br>Сравнительно эмоционально устойчивые - " + countOfStable +
                "<Br>Эмоционально устойчивые - " + countOfDeepStable + "</p><p> Честно ответили на вопросы тестов - " +
                countOfTrue + "<Br>Солгали - " + countOfFalse + "</p>";

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(String.format(STANDARD_RESPONSE, statisticMessage));
    }
}
