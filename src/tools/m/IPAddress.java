package tools.m;

/**
 * Klasa reprezentująca adres IP.
 * @author mrkaczor
 */
public class IPAddress implements Cloneable {

    public static final int OCTETS_COUNT = 4;
    private int[] octet;

    public IPAddress() {
        octet = new int[OCTETS_COUNT];
    }

    public IPAddress(String address) throws IllegalArgumentException {
        octet = new int[OCTETS_COUNT];
        parseFromString(address);
    }

    private void parseFromString(String address) {
        String[] octets = address.split("\\.");
        int i = 0, temp;
        if (octets.length == OCTETS_COUNT) {
            try {
                for (i = 0; i < OCTETS_COUNT; i++) {
                    temp = Integer.parseInt(octets[i]);
                    if (temp >= 0 && temp <= 255) {
                        octet[i] = temp;
                    } else {
                        throw new IllegalArgumentException("Podany adres IP jest nieprawidłowy (oktet " + (i + 1) + " ma niedozwoloną wartość)!");
                    }
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("W oktecie " + (i + 1) + " adresu IP wystąpił błąd: " + ex.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Podany adres IP jest nieprawidłowy!");
        }
    }

    public int getOctet(int octetId) {
        if (octetId > 0 && octetId <= OCTETS_COUNT) {
            return octet[octetId - 1];
        }
        return -1;
    }

    public void setOctet(int octetId, int value) {
        if (octetId > 0 && octetId <= OCTETS_COUNT) {
            if (value >= 0 && value <= 255) {
                octet[octetId - 1] = value;
            }
        }
    }

    @Override
    public IPAddress clone() {
        IPAddress copy = new IPAddress();
        for (int i = 0; i < OCTETS_COUNT; i++) {
            copy.setOctet(i + 1, octet[i]);
        }
        return copy;
    }

    @Override
    public String toString() {
        return octet[0] + "." + octet[1] + "." + octet[2] + "." + octet[3];
    }

}
