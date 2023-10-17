package Utils;


public class DataProvider {

    @org.testng.annotations.DataProvider
    public Object[][] user_data(){
        return new Object[][]{
                {"Test24", "User24", "contact_list24@gmail.com", "MyPassword24", "POST"},
        };
    }

    @org.testng.annotations.DataProvider
    public Object[][] update_user_data(){
        return new Object[][]{
                {"Test3patch", "User3patch", "contact_list24patch@gmail.com", "MyPassword24patch", "PATCH"},
        };
    }

    @org.testng.annotations.DataProvider
    public Object[][] login_user_data(){
        return new Object[][]{
                {"contact_list24@gmail.com", "MyPassword24"},
        };
    }
    @org.testng.annotations.DataProvider
    public Object[][] add_contact_data(){
        return new Object[][]{
                {"Test1patch", "User1patch", "jan_2001","contact_list1patch@gmail.com", "1234567890", "street1","street2", "bangalore","province", 1234, "india"},
        };
    }
}
