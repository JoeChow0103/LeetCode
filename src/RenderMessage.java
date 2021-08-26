import java.util.*;

public class RenderMessage {
    public String[] renderMessages(String[][] messages, int width, int userWidth) {
        String startEnd = "";
        startEnd += "+";
        for (int i = 0; i < width - 2; i++) {
            startEnd +="*";
        }
        startEnd += "+";
//        System.out.println(startEnd);

        List<String> res = new ArrayList<>();
        res.add(startEnd);
        int row = messages.length, col = messages[0].length;
        for (int i = 0; i < row; i++) {
            String[] message = messages[i];
            if (message[0].equals("1")) {
                handlerMessageForUserOne(message[1], width, userWidth, res);
            }
            if (message[0].equals("2")){
                handlerMessageForUserTwo(message[1], width, userWidth, res);
            }
        }
        res.add(startEnd);

        String[] finalStr = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            finalStr[i] = res.get(i);
        }
        return finalStr;
    }

    void handlerMessageForUserOne(String message, int width, int userWidth, List<String> res) {
        String[] words = message.split("\\s+");

        String line = new String();
        line += "|"; // add first "+"
        String curContext = "";
        String preContext = "";
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            curContext += temp;
            curContext += " ";
            int curLen = curContext.length();
            int preLen = preContext.length();
            if (curLen == userWidth + 1) {
                line += curContext.substring(0, curLen -1); // remove space
                for (int j = 0; j < width - curLen - 1; j++) {
                    line += " ";
                }
                line += "|"; // / add last "+"
                res.add(line);
                curContext = ""; // backtracing
                preContext = "";
                line = new String();
                line += "|"; // add first "+"
            } else if (curLen > userWidth + 1) {
                line += preContext.substring(0, preLen - 1); // backtrack to pre
                for (int j = 0; j < width - preLen - 1; j++) {
                    line += " ";
                }
                line += "|"; // / add last "+"
                res.add(line);

                curContext = curContext.substring(preLen, curLen);
                preContext = curContext;
//                System.out.println(curContext);
                line = new String();
                line += "|"; // add first "+"
            } else {
                preContext = curContext;
                continue;
            }
        }
        if (curContext.length() < userWidth) {// postprecessing
            line += curContext.substring(0, curContext.length() - 1); // remove space
            for (int j = 0; j < width - curContext.length() - 1; j++) {
                line += " ";
            }
            line += "|"; // / add last "+"
            res.add(line);
            line = new String();
            line += "|"; // add first "+"
        }
    }

    void handlerMessageForUserTwo(String message, int width, int userWidth, List<String> res) {
        String[] words = message.split("\\s+");

        String line = new String();
        line += "|"; // add first "+"
        String curContext = "";
        String preContext = "";
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            curContext += temp;
            curContext += " ";
            int curLen = curContext.length();
            int preLen = preContext.length();
            if (curLen == userWidth + 1) {
                for (int j = 0; j < width - curLen - 1; j++) {
                    line += " ";
                }
                line += curContext.substring(0, curLen -1); // remove space

                line += "|"; // / add last "+"
                res.add(line);
                curContext = "";
                preContext = "";
                line = new String();
                line += "|"; // add first "+"
            } else if (curLen > userWidth + 1) {
                for (int j = 0; j < width - preLen - 1; j++) {
                    line += " ";
                }
                line += preContext.substring(0, preLen - 1); // backtrack to pre

                line += "|"; // / add last "+"
                res.add(line);

                curContext = curContext.substring(preLen, curLen);
                preContext = curContext;
//                System.out.println(curContext);
                line = new String();
                line += "|"; // add first "+"
            } else {
                preContext = curContext;
                continue;
            }
        }
        if (curContext.length() < userWidth) {
            for (int j = 0; j < width - curContext.length() - 1; j++) {
                line += " ";
            }
            line += curContext.substring(0, curContext.length() - 1); // remove space

            line += "|"; // / add last "+"
            res.add(line);
            line = new String();
            line += "|"; // add first "+"
        }
    }


    public static void main(String[] args) {
        String[][] messages = {{"1", "hello how r u"}, {"2", "good ty"}};
        RenderMessage solution = new RenderMessage();
        String[] res = solution.renderMessages(messages, 15, 5);
        for (String s : res) System.out.println(s);
    }
}
