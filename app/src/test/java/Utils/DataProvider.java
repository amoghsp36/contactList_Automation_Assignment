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
                {"contact_list22@gmail.com", "MyPassword22"},
        };
    }
    @org.testng.annotations.DataProvider
    public Object[][] add_contact_data(){
        return new Object[][]{
                {"TestContact2", "UserContact2", "1999-03-08","contact_list1contact@gmail.com", "9876543210", "street2","street3", "bangalore","province", "12345", "india"},
        };
    }
}
