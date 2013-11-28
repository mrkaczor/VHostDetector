package tools.m;

/**
 * Klasa reprezentująca adres IP.
 * @author mrkaczor
 */
public class IPAddress implements Cloneable {

    public static final int OCTETS_COUNT = 4;
    private byte[] octet;

    public IPAddress() {
        octet = new byte[OCTETS_COUNT];
    }

    public IPAddress(String address) throws IllegalArgumentException {
        octet = new byte[OCTETS_COUNT];
        parseFromString(address);
    }

    private void parseFromString(String address) {
        String[] octets = address.split("\\.");
        byte i = 0, temp;
        if (octets.length == OCTETS_COUNT) {
            try {
                for (i = 0; i < OCTETS_COUNT; i++) {
                    temp = (byte)Integer.parseInt(octets[i]);
                    if ((temp & 0xFF)>=0 && (temp & 0xFF)<=255) {
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
            return octet[octetId - 1] & 0xFF;
        }
        return -1;
    }

    public void setOctet(int octetId, byte value) {
        if (octetId > 0 && octetId <= OCTETS_COUNT) {
            if ((value & 0xFF)>=0 && (value & 0xFF)<=255) {
                octet[octetId - 1] = value;
            }
        }
    }

    @Override
    public IPAddress clone() {
        IPAddress copy = new IPAddress();
        for (int i = 1; i <= OCTETS_COUNT; i++) {
            copy.setOctet(i, octet[i-1]);
        }
        return copy;
    }

    @Override
    public String toString() {
        return (octet[0] & 0xFF) + "." + (octet[1] & 0xFF) + "." + (octet[2] & 0xFF) + "." + (octet[3] & 0xFF);
    }

}
