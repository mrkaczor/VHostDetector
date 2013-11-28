package tools.m;

/**
 * Klasa reprezentująca zakres adresów IP, zgodny z notacją CIDR (adres bazowy +
 * maska).
 *
 * @author mrkaczor
 */
public class IPRange {

    private IPAddress _baseAddress;
    private byte[] _octetMin;
    private byte[] _octetMax;
    private int _mask;

    public IPRange(String address, int mask) {
        _baseAddress = new IPAddress(address);
        _mask = mask;
        initialize();
    }

    private void initialize() {
        int constOctets = _mask / 8;
        int power = 8 - _mask % 8;
        _octetMin = new byte[IPAddress.OCTETS_COUNT];
        _octetMax = new byte[IPAddress.OCTETS_COUNT];
        switch (constOctets) {
            case 0:
                _octetMin[0] = (byte)(_baseAddress.getOctet(1));
                _octetMax[0] = (byte)(_octetMin[0] + (1 << power) - 1);
                _octetMin[1] = _octetMin[2] = _octetMin[3] = 0;
                _octetMax[1] = _octetMax[2] = _octetMax[3] = (byte)255;
                break;

            case 1:
                _octetMin[0] = _octetMax[0] = (byte)(_baseAddress.getOctet(1));
                _octetMin[1] = (byte)(_baseAddress.getOctet(2));
                _octetMax[1] = (byte)(_octetMin[1] + (1 << power) - 1);
                _octetMin[2] = _octetMin[3] = 0;
                _octetMax[2] = _octetMax[3] = (byte)255;
                break;

            case 2:
                _octetMin[0] = _octetMax[0] = (byte)(_baseAddress.getOctet(1));
                _octetMin[1] = _octetMax[1] = (byte)(_baseAddress.getOctet(2));
                _octetMin[2] = (byte)(_baseAddress.getOctet(3));
                _octetMax[2] = (byte)(_octetMin[2] + (1 << power) - 1);
                _octetMin[3] = 0;
                _octetMax[3] = (byte)255;
                break;

            case 3:
                _octetMin[0] = _octetMax[0] = (byte)(_baseAddress.getOctet(1));
                _octetMin[1] = _octetMax[1] = (byte)(_baseAddress.getOctet(2));
                _octetMin[2] = _octetMax[2] = (byte)(_baseAddress.getOctet(3));
                _octetMin[3] = (byte)(_baseAddress.getOctet(4));
                _octetMax[3] = (byte)(_octetMin[3] + (1 << power) - 1);
                break;

            case 4:
                _octetMin[0] = _octetMax[0] = (byte)(_baseAddress.getOctet(1));
                _octetMin[1] = _octetMax[1] = (byte)(_baseAddress.getOctet(2));
                _octetMin[2] = _octetMax[2] = (byte)(_baseAddress.getOctet(3));
                _octetMin[3] = _octetMax[3] = (byte)(_baseAddress.getOctet(4));
                break;
        }
    }

    public IPAddress getBaseAddress() {
        return _baseAddress;
    }

    public int getMask() {
        return _mask;
    }

    public int getOctetMaxValue(int octetId) {
        if(octetId > 0 && octetId <= IPAddress.OCTETS_COUNT) {
            return _octetMax[octetId-1] & 0xFF;
        }
        return -1;
    }

    public int getOctetMinValue(int octetId) {
        if(octetId > 0 && octetId <= IPAddress.OCTETS_COUNT) {
            return _octetMin[octetId-1] & 0xFF;
        }
        return -1;
    }

    @Override
    public String toString() {
    	String range = _baseAddress+"/"+_mask+" -> "; 
    	range += "[" + (_octetMin[0] == _octetMax[0] ? _octetMin[0] & 0xFF : (_octetMin[0] & 0xFF) + "-" + (_octetMax[0] & 0xFF)) + "].";
    	range += "[" + (_octetMin[1] == _octetMax[1] ? _octetMin[1] & 0xFF : (_octetMin[1] & 0xFF) + "-" + (_octetMax[1] & 0xFF)) + "].";
        range += "[" + (_octetMin[2] == _octetMax[2] ? _octetMin[2] & 0xFF : (_octetMin[2] & 0xFF) + "-" + (_octetMax[2] & 0xFF)) + "].";
        range += "[" + (_octetMin[3] == _octetMax[3] ? _octetMin[3] & 0xFF : (_octetMin[3] & 0xFF) + "-" + (_octetMax[3] & 0xFF)) + "]";
    	return range;
    }

}
