etc add to trx add


核心代码：

public static string GetBase58CheckAddress(string ethAddress)
{
    string fixaddress = "0x41" + ethAddress.RemoveHexPrefix();

    byte[] addressBytes = fixaddress.HexToByteArray();

    byte[] hash0 = SHA256(addressBytes);
    byte[] hash1 = SHA256(hash0);

    var checkSum = hash1.Take(4).ToArray();

    return Base58.Encode(addressBytes.Concat(checkSum).ToArray());
}



1个


下面的代码可以解决问题：

const HEX_PREFIX = '41';

exports.hexAddressToBase58 = (hexAddress) => {
    let retval = hexAddress;
    try {
        let tronWeb = getTronWeb();
        if (hexAddress.startsWith("0x")) {
            hexAddress = HEX_PREFIX + hexAddress.substring(2);
        }
        let bArr = tronWeb.utils['code'].hexStr2byteArray(hexAddress);
        retval = tronWeb.utils['crypto'].getBase58CheckAddress(bArr);
    } catch (e) {
        //Handle
    }
    return retval;
}