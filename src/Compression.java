/**
 * Created by yrafiyev on 2017-07-07.
 */
public class Compression {


    private void compress(String str){
        String compressed = "";
        int counter = 0;
        int i = 0;
        while(i < str.length()){
            int j = i;
            while(j < str.length()){
                if(str.charAt(i) == str.charAt(j)) {counter++; j++;}
                else break;
            }
            compressed += (Integer.toString(counter) + str.charAt(i));
            counter = 0;
            i = j;
        }
        System.out.println(compressed);
    }

    public static void main(String[] args){
        String string = "aaabbbbccccdddw1cc";
        String empty = "";
        String one = "a";
        String change = "aaaaaaaabbbbbbbc";
        Compression comp = new Compression();
        comp.compress(string);
        comp.compress(empty);
        comp.compress(one);
        comp.compress(change);
    }
}
