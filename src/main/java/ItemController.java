import org.hibernate.HibernateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")

public class ItemController extends HttpServlet {

    private static final ItemService itemService = new ItemService();

  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      // Item item = new Item();
     // метод должен возвращать в окно браузера введенное значени
       String params = req.getParameter("id");
       resp.getWriter().println(itemService.servRead(params));
   }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = new Item();
        //считываем стрим приходящий из Постмана (метод POST)
        //мапим данные Гибернейтом
        //добавляем ай ди и даты
        //сохраняем в БД

        item.setName(req.getParameter("name"));
        item.setDescription(req.getParameter("description"));
        itemService.servSave(item);
        resp.getWriter().println(item);
    }


    //делаем запрос req.getParameter("itemName") вызываем параметр  "itemName"
    // и по имени которое получили делаем обновление  объекта из БД
   @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
       //Item item = new Item();
       long idServ = Long.parseLong(req.getParameter("id"));
        itemService.servUpdate(idServ);
       resp.getWriter().println(idServ);
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws HibernateException {

        long idServ = Long.parseLong(req.getParameter("id"));
        itemService.servDelete(idServ);

        //делаем запрос req.getParameter("itemId") вызываем параметр  "itemId"
        // и по айдишнику который получили делаем удаление объекта из БД
    }
}
