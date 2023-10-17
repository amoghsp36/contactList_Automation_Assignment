package Utils;


public class DataProvider {

    @org.testng.annotations.DataProvider
    public Object[][] user_data(){
        return new Object[][]{
                {"Test7", "User7", "contact_list7@gmail.com", "MyPassword7", "POST"},
                {"Test2", "User2", "contact_list2@gmail.com", "MyPassword2", "GET"}
        };
    }
}
