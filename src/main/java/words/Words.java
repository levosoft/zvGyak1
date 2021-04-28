package words;

public class Words {
    public boolean hasMoreDigits(String s) {

        int letterNum = 0;
        int digitNum = 0;

        for (char c: s.toCharArray()) {
            //System.out.println(Character.isLetter(c));
            if (Character.isLetter(c)){
                letterNum++;
            }else{
                digitNum++;
            }
        }

        System.out.println(digitNum + " " + letterNum);

        if (digitNum > letterNum) {
            return true;
        }else if (digitNum < letterNum){
            return true;
        } else {
            return false;
        }
    }
}
