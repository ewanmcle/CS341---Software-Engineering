package src;

public class Password {

    char[] pass;

    Password (char[] p) {
        pass = p;
    }

    public String getPassword(){
        String output = new String(pass);
        return output;
    }

    public String testPassword(){

        int high = 1;
        int count = 1;
        String output = "";
        int fix = 0;

        if (pass.length >= 8 && pass.length <= 12) {
            for (int i = 0; i < pass.length - 1; i++){
                char current = pass[i];
                if (current == pass[i + 1]) {
                    count++;
                } else count = 1;

                if (count > high) {
                    high = count;
                }
            }
            fix = high - 2;

            output = "The largest block in the password is " + high + "\n";

            if (high < 3) output += "This is a decent password.";
            if (high > 2) output += "This is a weak password. \nThis Password can be stronger by reducing the block by " + fix;

            return output;
        } else if (pass.length < 8){
        	return "Password is too short.";
        } else return "Password is too long.";

        

    }

}








