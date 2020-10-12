package GUI_admin;

import java.util.ArrayList;
import java.util.BitSet;

public class BitsetToHex {

    /**
     * Turns a bitset into a hex String to be written
     *
     * @param instruction bitset of the instruction
     * @return
     */
    public static String byteToHex(BitSet instruction) {
        byte[] bytearray = instruction.toByteArray();
        String toReturn = "";
        for (byte b : bytearray) {
            String st = String.format("%02X", b);
            toReturn = st + toReturn;
        }
        return toReturn;
    }

    private static String grtBitString(BitSet bits, int size) {
        int i = 0;
        String str = "";
        while (i != size) {
            if (i == size) {
                break;
            }
            if (bits.get(i)) {
                str = "1" + str;
            } else {
                str = "0" + str;

            }
            i++;
        }
        return str;
    }

    public static String bitToHex(BitSet bits, int size) {
        if (size == 0) {
            return "0";
        }
        if (size % 4 != 0) {
            size = size + (4 - size % 4);
        }
        String lines = grtBitString(bits, size);
        int len = lines.length();
        int i = 0;
        ArrayList<String> halfByte = new ArrayList<>();
        while (i != len) {
            String toAdd = "";
            int j = 0;
            while (j != 4) {
                toAdd = toAdd + lines.charAt(i);
                i++;
                j++;
            }
            halfByte.add(toAdd);
        }
        String hexCode = "";
        for (String str : halfByte) {
            hexCode = hexCode + getHexEq(str);
        }
        return hexCode;
    }

    public static String getHexEq(String eq) {
        switch (eq) {
            case "0000":
                return "0";
            case "0001":
                return "1";
            case "0010":
                return "2";
            case "0011":
                return "3";
            case "0100":
                return "4";
            case "0101":
                return "5";
            case "0110":
                return "6";
            case "0111":
                return "7";
            case "1000":
                return "8";
            case "1001":
                return "9";
            case "1010":
                return "A";
            case "1011":
                return "B";
            case "1100":
                return "C";
            case "1101":
                return "D";
            case "1110":
                return "E";
            case "1111":
                return "F";
        }
        return null;
    }

    //    public String getHexBit(String dir){
//        char lastHex= dir.charAt(dir.length()-1);
//        switch (lastHex){
//            case '0' :
//                return "0000";
//            case '1':
//                return "0001";
//            case '2':
//                return "2";
//            case '3':
//                return "3";
//            case '4':
//                return "4";
//            case '5':
//                return "5";
//            case '6':
//                return "6";
//            case '7':
//                return "7";
//            case '8':
//                return "8";
//            case '9':
//                return "9";
//            case 'A':
//                return "A";
//            case "1011":
//                return "B";
//            case "1100":
//                return "C";
//            case "1101":
//                return "D";
//            case "1110":
//                return "E";
//            case "1111":
//                return "F";
//        }
//    }
    public static String getHexBit(char hex) {
        switch (hex) {
            case '0' :
                return "0000";
            case '1' :
                return "0001";
            case '2' :
                return "0010";
            case '3' :
                return "0011";
            case '4' :
                return "0100";
            case '5' :
                return "0101";
            case '6' :
                return "0110";
            case '7' :
                return "0111";
            case '8' :
                return "1000";
            case '9' :
                return "1001";
            case 'A' :
                return "1010";
            case 'B' :
                return "1011";
            case 'C' :
                return "1100";
            case 'D' :
                return "1101";
            case 'E' :
                return "1110";
            case 'F' :
                return "1111";
            default:
                return null;
        }
    }
}
