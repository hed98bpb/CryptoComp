public class LayoutConfig
{
    public boolean a, abar, b, bbar, c, cbar, d, dbar, e, ebar, f, fbar;

    public LayoutConfig(Bloodtype donor, Bloodtype recipient){
        if (donor.encoding.charAt(0) == "1".charAt(0)){
            a = true;
            abar = false;
        } else {
            a = false;
            abar = true;
        }
        if (donor.encoding.charAt(1) == "1".charAt(0)) {
            b = true;
            bbar = false;
        } else {
            b = false;
            bbar = true;
        }
        if (donor.encoding.charAt(2) == "1".charAt(0)){
            c = true;
            cbar = false;
        } else {
            c = false;
            cbar = true;
        }
        if (recipient.encoding.charAt(0) == "1".charAt(0)){
            d = true;
            dbar = false;
        } else {
            d = false;
            dbar = true;
        }
        if (recipient.encoding.charAt(1) == "1".charAt(0)){
            e = true;
            ebar = false;
        } else {
            e = false;
            ebar = true;
        }
        if (recipient.encoding.charAt(2) == "1".charAt(0)){
            f = true;
            fbar = false;
        } else {
            f = false;
            fbar = true;
        }
    }
}
