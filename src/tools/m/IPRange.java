package tools.m;

/**
 * Klasa reprezentująca zakres adresów IP, zgodny z notacją CIDR (adres bazowy +
 * maska).
 *
 * @author mrkaczor
 */
public class IPRange {

    private final int OCTET_COUNT = 4;
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
        switch (constOctets) {
            case 0:
                _octetMin[0] = _baseAddress.getOctet(1);
                _octetMax[0] = 1 << power;
                _octetMin[1] = _octetMin[2] = _octetMin[3] = 0;
                _octetMax[1] = _octetMax[2] = _octetMax[3] = 255;
                break;

            case 1:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _baseAddress.getOctet(2);
                _octetMax[1] = 1 << power;
                _octetMin[2] = _octetMin[3] = 0;
                _octetMax[2] = _octetMax[3] = 255;
                break;

            case 2:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _baseAddress.getOctet(3);
                _octetMax[2] = 1 << power;
                _octetMin[3] = 0;
                _octetMax[3] = 255;
                break;

            case 3:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _octetMax[2] = _baseAddress.getOctet(3);
                _octetMin[3] = _baseAddress.getOctet(3);
                _octetMax[3] = 1 << power;
                break;

            case 4:
                _octetMin[0] = _octetMax[0] = _baseAddress.getOctet(1);
                _octetMin[1] = _octetMax[1] = _baseAddress.getOctet(2);
                _octetMin[2] = _octetMax[2] = _baseAddress.getOctet(3);
                _octetMin[3] = _octetMax[3] = _baseAddress.getOctet(4);
                break;
        }
        String debug_msg = "Initialized address range: [" + (_octetMin[0] == _octetMax[0] ? _octetMin[0] : _octetMin[0] + "-" + _octetMax[0]) + "].";
        debug_msg += "[" + (_octetMin[1] == _octetMax[1] ? _octetMin[1] : _octetMin[1] + "-" + _octetMax[1]) + "].";
        debug_msg += "[" + (_octetMin[2] == _octetMax[2] ? _octetMin[2] : _octetMin[2] + "-" + _octetMax[2]) + "].";
        debug_msg += "[" + (_octetMin[3] == _octetMax[3] ? _octetMin[3] : _octetMin[3] + "-" + _octetMax[3]) + "]";
        System.out.println(debug_msg);
    }

    public IPAddress getBaseAddress() {
        return _baseAddress;
    }

    public int getMask() {
        return _mask;
    }

    public int getOctetMaxValue(int octetId) {
        if(octetId > 0 && octetId <= OCTET_COUNT) {
            return _octetMax[octetId];
        }
        return -1;
    }

    public int getOctetMinValue(int octetId) {
        if(octetId > 0 && octetId <= OCTET_COUNT) {
            return _octetMin[octetId];
        }
        return -1;
    }

}
