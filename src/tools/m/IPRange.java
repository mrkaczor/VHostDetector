package tools.m;

/**
 * Klasa reprezentująca zakres adresów IP, zgodny z notacją CIDR (adres bazowy +
 * maska).
 *
 * @author mrkaczor
 */
public class IPRange {

    private IPAddress _baseAddress;
    private int[] _octetMin;
    private int[] _octetMax;
    private int _mask;

    public IPRange(String address, int mask) {
        _baseAddress = new IPAddress(address);
        _mask = mask;
        initialize();
    }

    private void initialize() {
        int constOctets = _mask / 8;
        int power = 8 - _mask % 8;
        _octetMin = new int[IPAddress.OCTETS_COUNT];
        _octetMax = new int[IPAddress.OCTETS_COUNT];
        switch (constOctets) {
            case 0:
                _octetMin[0] = _baseAddress.getOctet(1);
                _octetMax[0] = _octetMin[0] + (1 << power) - 1;
                _octetMin[1] = _octetMin[2] = _octetMin[3] = 0;
                _octetMax[1] = _octetMax[2] = _octetMax[3] = 255;
                break;

            case 1:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _baseAddress.getOctet(2);
                _octetMax[1] = _octetMin[1] + (1 << power) - 1;
                _octetMin[2] = _octetMin[3] = 0;
                _octetMax[2] = _octetMax[3] = 255;
                break;

            case 2:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _baseAddress.getOctet(3);
                _octetMax[2] = _octetMin[2] + (1 << power) - 1;
                _octetMin[3] = 0;
                _octetMax[3] = 255;
                break;

            case 3:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _octetMax[2] = _baseAddress.getOctet(3);
                _octetMin[3] = _baseAddress.getOctet(4);
                _octetMax[3] = _octetMin[3] + (1 << power) - 1;
                break;

            case 4:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _octetMax[2] = _baseAddress.getOctet(3);
                _octetMin[3] = _octetMax[3] = _baseAddress.getOctet(4);
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
            return _octetMax[octetId-1];
        }
        return -1;
    }

    public int getOctetMinValue(int octetId) {
        if(octetId > 0 && octetId <= IPAddress.OCTETS_COUNT) {
            return _octetMin[octetId-1];
        }
        return -1;
    }

    @Override
    public String toString() {
    	String range = _baseAddress+"/"+_mask+" -> "; 
    	range += "[" + (_octetMin[0] == _octetMax[0] ? _octetMin[0] : _octetMin[0] + "-" + _octetMax[0]) + "].";
    	range += "[" + (_octetMin[1] == _octetMax[1] ? _octetMin[1] : _octetMin[1] + "-" + _octetMax[1]) + "].";
        range += "[" + (_octetMin[2] == _octetMax[2] ? _octetMin[2] : _octetMin[2] + "-" + _octetMax[2]) + "].";
        range += "[" + (_octetMin[3] == _octetMax[3] ? _octetMin[3] : _octetMin[3] + "-" + _octetMax[3]) + "]";
    	return range;
    }

}
