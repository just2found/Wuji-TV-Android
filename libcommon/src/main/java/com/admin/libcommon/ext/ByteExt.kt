package com.admin.libcommon.ext

import kotlin.experimental.and
import kotlin.experimental.xor

/**
 * Byte数组ext
 * create by admin on 2019/12/26 10:58
 */

private val HEX_CHAR =
  charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

/**
 * byte数组转换成16进制字符串
 * create by admin on 2019/12/26 10:59
 */
fun ByteArray.toHexString(): String {

  val buf = CharArray(this.size * 2)
  var a: Int
  var index = 0
  for (b in this) {
    // 使用除与取余进行转换
    a = if (b < 0) {
      256 + b
    } else {
      b.toInt()
    }
    buf[index++] = HEX_CHAR[a / 16]
    buf[index++] = HEX_CHAR[a % 16]
  }
  return String(buf)
}

internal var CRC8_TAB = byteArrayOf(
  0x00.toByte(),
  0x07.toByte(),
  0x0E.toByte(),
  0x09.toByte(),
  0x1C.toByte(),
  0x1B.toByte(),
  0x12.toByte(),
  0x15.toByte(),
  0x38.toByte(),
  0x3F.toByte(),
  0x36.toByte(),
  0x31.toByte(),
  0x24.toByte(),
  0x23.toByte(),
  0x2A.toByte(),
  0x2D.toByte(),
  0x70.toByte(),
  0x77.toByte(),
  0x7E.toByte(),
  0x79.toByte(),
  0x6C.toByte(),
  0x6B.toByte(),
  0x62.toByte(),
  0x65.toByte(),
  0x48.toByte(),
  0x4F.toByte(),
  0x46.toByte(),
  0x41.toByte(),
  0x54.toByte(),
  0x53.toByte(),
  0x5A.toByte(),
  0x5D.toByte(),
  0xE0.toByte(),
  0xE7.toByte(),
  0xEE.toByte(),
  0xE9.toByte(),
  0xFC.toByte(),
  0xFB.toByte(),
  0xF2.toByte(),
  0xF5.toByte(),
  0xD8.toByte(),
  0xDF.toByte(),
  0xD6.toByte(),
  0xD1.toByte(),
  0xC4.toByte(),
  0xC3.toByte(),
  0xCA.toByte(),
  0xCD.toByte(),
  0x90.toByte(),
  0x97.toByte(),
  0x9E.toByte(),
  0x99.toByte(),
  0x8C.toByte(),
  0x8B.toByte(),
  0x82.toByte(),
  0x85.toByte(),
  0xA8.toByte(),
  0xAF.toByte(),
  0xA6.toByte(),
  0xA1.toByte(),
  0xB4.toByte(),
  0xB3.toByte(),
  0xBA.toByte(),
  0xBD.toByte(),
  0xC7.toByte(),
  0xC0.toByte(),
  0xC9.toByte(),
  0xCE.toByte(),
  0xDB.toByte(),
  0xDC.toByte(),
  0xD5.toByte(),
  0xD2.toByte(),
  0xFF.toByte(),
  0xF8.toByte(),
  0xF1.toByte(),
  0xF6.toByte(),
  0xE3.toByte(),
  0xE4.toByte(),
  0xED.toByte(),
  0xEA.toByte(),
  0xB7.toByte(),
  0xB0.toByte(),
  0xB9.toByte(),
  0xBE.toByte(),
  0xAB.toByte(),
  0xAC.toByte(),
  0xA5.toByte(),
  0xA2.toByte(),
  0x8F.toByte(),
  0x88.toByte(),
  0x81.toByte(),
  0x86.toByte(),
  0x93.toByte(),
  0x94.toByte(),
  0x9D.toByte(),
  0x9A.toByte(),
  0x27.toByte(),
  0x20.toByte(),
  0x29.toByte(),
  0x2E.toByte(),
  0x3B.toByte(),
  0x3C.toByte(),
  0x35.toByte(),
  0x32.toByte(),
  0x1F.toByte(),
  0x18.toByte(),
  0x11.toByte(),
  0x16.toByte(),
  0x03.toByte(),
  0x04.toByte(),
  0x0D.toByte(),
  0x0A.toByte(),
  0x57.toByte(),
  0x50.toByte(),
  0x59.toByte(),
  0x5E.toByte(),
  0x4B.toByte(),
  0x4C.toByte(),
  0x45.toByte(),
  0x42.toByte(),
  0x6F.toByte(),
  0x68.toByte(),
  0x61.toByte(),
  0x66.toByte(),
  0x73.toByte(),
  0x74.toByte(),
  0x7D.toByte(),
  0x7A.toByte(),
  0x89.toByte(),
  0x8E.toByte(),
  0x87.toByte(),
  0x80.toByte(),
  0x95.toByte(),
  0x92.toByte(),
  0x9B.toByte(),
  0x9C.toByte(),
  0xB1.toByte(),
  0xB6.toByte(),
  0xBF.toByte(),
  0xB8.toByte(),
  0xAD.toByte(),
  0xAA.toByte(),
  0xA3.toByte(),
  0xA4.toByte(),
  0xF9.toByte(),
  0xFE.toByte(),
  0xF7.toByte(),
  0xF0.toByte(),
  0xE5.toByte(),
  0xE2.toByte(),
  0xEB.toByte(),
  0xEC.toByte(),
  0xC1.toByte(),
  0xC6.toByte(),
  0xCF.toByte(),
  0xC8.toByte(),
  0xDD.toByte(),
  0xDA.toByte(),
  0xD3.toByte(),
  0xD4.toByte(),
  0x69.toByte(),
  0x6E.toByte(),
  0x67.toByte(),
  0x60.toByte(),
  0x75.toByte(),
  0x72.toByte(),
  0x7B.toByte(),
  0x7C.toByte(),
  0x51.toByte(),
  0x56.toByte(),
  0x5F.toByte(),
  0x58.toByte(),
  0x4D.toByte(),
  0x4A.toByte(),
  0x43.toByte(),
  0x44.toByte(),
  0x19.toByte(),
  0x1E.toByte(),
  0x17.toByte(),
  0x10.toByte(),
  0x05.toByte(),
  0x02.toByte(),
  0x0B.toByte(),
  0x0C.toByte(),
  0x21.toByte(),
  0x26.toByte(),
  0x2F.toByte(),
  0x28.toByte(),
  0x3D.toByte(),
  0x3A.toByte(),
  0x33.toByte(),
  0x34.toByte(),
  0x4E.toByte(),
  0x49.toByte(),
  0x40.toByte(),
  0x47.toByte(),
  0x52.toByte(),
  0x55.toByte(),
  0x5C.toByte(),
  0x5B.toByte(),
  0x76.toByte(),
  0x71.toByte(),
  0x78.toByte(),
  0x7F.toByte(),
  0x6A.toByte(),
  0x6D.toByte(),
  0x64.toByte(),
  0x63.toByte(),
  0x3E.toByte(),
  0x39.toByte(),
  0x30.toByte(),
  0x37.toByte(),
  0x22.toByte(),
  0x25.toByte(),
  0x2C.toByte(),
  0x2B.toByte(),
  0x06.toByte(),
  0x01.toByte(),
  0x08.toByte(),
  0x0F.toByte(),
  0x1A.toByte(),
  0x1D.toByte(),
  0x14.toByte(),
  0x13.toByte(),
  0xAE.toByte(),
  0xA9.toByte(),
  0xA0.toByte(),
  0xA7.toByte(),
  0xB2.toByte(),
  0xB5.toByte(),
  0xBC.toByte(),
  0xBB.toByte(),
  0x96.toByte(),
  0x91.toByte(),
  0x98.toByte(),
  0x9F.toByte(),
  0x8A.toByte(),
  0x8D.toByte(),
  0x84.toByte(),
  0x83.toByte(),
  0xDE.toByte(),
  0xD9.toByte(),
  0xD0.toByte(),
  0xD7.toByte(),
  0xC2.toByte(),
  0xC5.toByte(),
  0xCC.toByte(),
  0xCB.toByte(),
  0xE6.toByte(),
  0xE1.toByte(),
  0xE8.toByte(),
  0xEF.toByte(),
  0xFA.toByte(),
  0xFD.toByte(),
  0xF4.toByte(),
  0xF3.toByte()
)

/**
 * crc-8校验 x8+x2+x+1
 */
fun ByteArray.crc8(): Byte {

  var x = 0
  var index: Int
  var xorResult: Byte

  xorResult = 0x00

  while (x < this.size) {
    index = (xorResult xor this[x]).toInt()
    //index & 0XFF是为了解决C语言无符号byte转换问题
    xorResult = CRC8_TAB[index and 0XFF]
    x++
  }
  return xorResult
}

var CRC16_CCITT_TABLE = intArrayOf(
  0x0000,
  0x1189,
  0x2312,
  0x329b,
  0x4624,
  0x57ad,
  0x6536,
  0x74bf,
  0x8c48,
  0x9dc1,
  0xaf5a,
  0xbed3,
  0xca6c,
  0xdbe5,
  0xe97e,
  0xf8f7,
  0x1081,
  0x0108,
  0x3393,
  0x221a,
  0x56a5,
  0x472c,
  0x75b7,
  0x643e,
  0x9cc9,
  0x8d40,
  0xbfdb,
  0xae52,
  0xdaed,
  0xcb64,
  0xf9ff,
  0xe876,
  0x2102,
  0x308b,
  0x0210,
  0x1399,
  0x6726,
  0x76af,
  0x4434,
  0x55bd,
  0xad4a,
  0xbcc3,
  0x8e58,
  0x9fd1,
  0xeb6e,
  0xfae7,
  0xc87c,
  0xd9f5,
  0x3183,
  0x200a,
  0x1291,
  0x0318,
  0x77a7,
  0x662e,
  0x54b5,
  0x453c,
  0xbdcb,
  0xac42,
  0x9ed9,
  0x8f50,
  0xfbef,
  0xea66,
  0xd8fd,
  0xc974,
  0x4204,
  0x538d,
  0x6116,
  0x709f,
  0x0420,
  0x15a9,
  0x2732,
  0x36bb,
  0xce4c,
  0xdfc5,
  0xed5e,
  0xfcd7,
  0x8868,
  0x99e1,
  0xab7a,
  0xbaf3,
  0x5285,
  0x430c,
  0x7197,
  0x601e,
  0x14a1,
  0x0528,
  0x37b3,
  0x263a,
  0xdecd,
  0xcf44,
  0xfddf,
  0xec56,
  0x98e9,
  0x8960,
  0xbbfb,
  0xaa72,
  0x6306,
  0x728f,
  0x4014,
  0x519d,
  0x2522,
  0x34ab,
  0x0630,
  0x17b9,
  0xef4e,
  0xfec7,
  0xcc5c,
  0xddd5,
  0xa96a,
  0xb8e3,
  0x8a78,
  0x9bf1,
  0x7387,
  0x620e,
  0x5095,
  0x411c,
  0x35a3,
  0x242a,
  0x16b1,
  0x0738,
  0xffcf,
  0xee46,
  0xdcdd,
  0xcd54,
  0xb9eb,
  0xa862,
  0x9af9,
  0x8b70,
  0x8408,
  0x9581,
  0xa71a,
  0xb693,
  0xc22c,
  0xd3a5,
  0xe13e,
  0xf0b7,
  0x0840,
  0x19c9,
  0x2b52,
  0x3adb,
  0x4e64,
  0x5fed,
  0x6d76,
  0x7cff,
  0x9489,
  0x8500,
  0xb79b,
  0xa612,
  0xd2ad,
  0xc324,
  0xf1bf,
  0xe036,
  0x18c1,
  0x0948,
  0x3bd3,
  0x2a5a,
  0x5ee5,
  0x4f6c,
  0x7df7,
  0x6c7e,
  0xa50a,
  0xb483,
  0x8618,
  0x9791,
  0xe32e,
  0xf2a7,
  0xc03c,
  0xd1b5,
  0x2942,
  0x38cb,
  0x0a50,
  0x1bd9,
  0x6f66,
  0x7eef,
  0x4c74,
  0x5dfd,
  0xb58b,
  0xa402,
  0x9699,
  0x8710,
  0xf3af,
  0xe226,
  0xd0bd,
  0xc134,
  0x39c3,
  0x284a,
  0x1ad1,
  0x0b58,
  0x7fe7,
  0x6e6e,
  0x5cf5,
  0x4d7c,
  0xc60c,
  0xd785,
  0xe51e,
  0xf497,
  0x8028,
  0x91a1,
  0xa33a,
  0xb2b3,
  0x4a44,
  0x5bcd,
  0x6956,
  0x78df,
  0x0c60,
  0x1de9,
  0x2f72,
  0x3efb,
  0xd68d,
  0xc704,
  0xf59f,
  0xe416,
  0x90a9,
  0x8120,
  0xb3bb,
  0xa232,
  0x5ac5,
  0x4b4c,
  0x79d7,
  0x685e,
  0x1ce1,
  0x0d68,
  0x3ff3,
  0x2e7a,
  0xe70e,
  0xf687,
  0xc41c,
  0xd595,
  0xa12a,
  0xb0a3,
  0x8238,
  0x93b1,
  0x6b46,
  0x7acf,
  0x4854,
  0x59dd,
  0x2d62,
  0x3ceb,
  0x0e70,
  0x1ff9,
  0xf78f,
  0xe606,
  0xd49d,
  0xc514,
  0xb1ab,
  0xa022,
  0x92b9,
  0x8330,
  0x7bc7,
  0x6a4e,
  0x58d5,
  0x495c,
  0x3de3,
  0x2c6a,
  0x1ef1,
  0x0f78
)

/**
 * crc-16校验/ccitt x16+x12+x5+1
 */
fun ByteArray.crc16(): Int {
  var crcReg = 0x0000
  for (i in this.indices) {
    crcReg = CRC16_CCITT_TABLE[crcReg xor this[i].toInt() and 0xFF] xor (crcReg shr 8)
  }
  return crcReg
}


/**
 * byte转16进制字符串
 */
fun Byte.toHexString(): String {
  var hex = Integer.toHexString((this and 0xFF.toByte()).toInt())
  if (hex.length < 2) {
    hex = "0$hex"
  }
  return hex
}
