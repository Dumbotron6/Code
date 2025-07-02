package strings;

public class ValidateIPAddress {
    /*
    https://leetcode.com/problems/validate-ip-address/
     */

    public String validIPAddress(String queryIP) {
        String[] strs = queryIP.split("\\.",-1); //-1 for edge case like "172.16.254."

        if(strs.length > 1) {
            if(strs.length != 4) {
                return "Neither";
            }
            for(String str : strs) {
                if(str.length() > 3 || str.length() == 0 || (str.length() > 1 && str.charAt(0) == '0')) {
                    return "Neither";
                }
                for(char c : str.toCharArray()) {
                    if(!(c >= '0' && c <= '9')) {
                        return "Neither";
                    }
                }
                if(Integer.parseInt(str) > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        }

        strs = queryIP.split(":",-1); //-1 for edge case like "2001:0db8:85a3:0:0:8A2E:0370:7334:"
        if(strs.length > 1) {
            if(strs.length != 8) {
                return "Neither";
            }
            for(String str : strs) {
                if(str.length() > 4 || str.length() == 0) {
                    return "Neither";
                }
                for(char c : str.toCharArray()) {
                    if(!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) {
                        return "Neither";
                    }
                }
            }
            return "IPv6";
        }

        return "Neither";
    }
}
