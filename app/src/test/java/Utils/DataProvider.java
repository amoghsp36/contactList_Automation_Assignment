package Utils;


public class DataProvider {

    @org.testng.annotations.DataProvider
    public Object[][] user_data(){
        return new Object[][]{
                {"Test31", "User31", "contact_list31@gmail.com", "MyPassword31", "POST"},
        };
    }

    @org.testng.annotations.DataProvider
    public Object[][] update_user_data(){
        return new Object[][]{
                {"Test9patch", "User9patch", "contact_list31patch@gmail.com", "MyPassword31patch", "PATCH"},
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
                {"TestContact14", "UserContact14", "2920-03-08","contact_list14contact@gmail.com", "9876543210", "street2","street3", "bangalore","province", "12345", "india"},
        };
    }

    @org.testng.annotations.DataProvider
    public Object[][] update_contact_data(){
        return new Object[][]{
                {"TestContact2put", "UserContact2put", "2009-05-10","contact_list1contactpatch@gmail.com", "7385873928", "street5","street6", "tokyo","shibuya", "555777", "japan"},
        };
    }
    @org.testng.annotations.DataProvider
    public Object[][] patch_contact_data(){
        return new Object[][]{
                {"patchedTest", "patchedUser", "2023-08-19"},
        };
    }
}
