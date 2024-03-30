## Module: ByteArray.java
模块名称: ByteArray.java

主要目标: 此模块的主要目标是提供处理字节数组的功能，包括将字符串转换为字节数组，将字节数组转换为字符串，将长整型转换为字节数组，将字节数组转换为长整型，将整型转换为字节数组，将字节数组转换为整型等操作。

关键功能: 
1. fromString(String s): 将字符串转换为字节数组。
2. toStr(byte[] b): 将字节数组转换为字符串。
3. fromLong(long val): 将长整型转换为字节数组。
4. toLong(byte[] b): 将字节数组转换为长整型。
5. fromInt(int val): 将整型转换为字节数组。
6. toInt(byte[] b): 将字节数组转换为整型。
7. compareUnsigned(byte[] a, byte[] b): 比较两个字节数组的无符号大小。
8. toHexString(byte[] data): 将字节数组转换为十六进制字符串。
9. fromHexString(String data): 将十六进制字符串转换为字节数组。

关键变量: 
1. EMPTY_BYTE_ARRAY: 空字节数组。
2. ZERO_BYTE_ARRAY: 包含一个零字节的字节数组。
3. WORD_SIZE: 字节大小为32。

相互依赖: 该模块与其他系统组件的交互主要是在数据转换和比较操作中。

核心与辅助操作: 核心操作包括数据转换和比较功能，辅助操作包括字符串处理和空字节数组定义等。

操作顺序: 模块中的操作顺序主要是根据功能需求进行调用，如先将字符串转换为字节数组，再将字节数组转换为长整型等。

性能方面: 考虑到数据转换和比较操作可能涉及大量数据，性能方面需要注意优化算法以提高效率。

可重用性: 该模块具有良好的可重用性，可以在不同的应用中用于字节数据的处理和转换。

用法: ByteArray模块可在需要进行字节数组和数据类型转换的程序中使用，如加密解密、网络通信等场景。

假设: 假设模块中的数据转换和比较操作需要处理有效的输入数据，不包含异常情况处理。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: ByteArray.java
    op2=>operation: package org.tron.plugins.utils;
    op3=>operation: import com.google.common.primitives.Ints;
    op4=>operation: import com.google.common.primitives.Longs;
    op5=>operation: import java.math.BigInteger;
    op6=>operation: import org.apache.commons.lang3.ArrayUtils;
    op7=>operation: import org.apache.commons.lang3.StringUtils;
    op8=>operation: import org.bouncycastle.util.encoders.Hex;
    op9=>operation: public class ByteArray {
    op10=>operation: public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    op11=>operation: public static final byte[] ZERO_BYTE_ARRAY = new byte[]{0};
    op12=>operation: public static final int WORD_SIZE = 32;
    op13=>operation: public static byte[] fromString(String s) {
        return StringUtils.isBlank(s) ? null : s.getBytes;
    }
    op14=>operation: public static String toStr(byte[] b) {
        return ArrayUtils.isEmpty(b) ? null : new String(b);
    }
    op15=>operation: public static byte[] fromLong(long val) {
        return Longs.toByteArray(val);
    }
    op16=>operation: public static long toLong(byte[] b) {
        return ArrayUtils.isEmpty(b) ? 0 : new BigInteger(1, b).longValue;
    }
    op17=>operation: public static byte[] fromInt(int val) {
        return Ints.toByteArray(val);
    }
    op18=>operation: public static int toInt(byte[] b) {
        return ArrayUtils.isEmpty(b) ? 0 : new BigInteger(1, b).intValue;
    }
    op19=>operation: public static int compareUnsigned(byte[] a, byte[] b) {
        if (a == b) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        int minLen = Math.min(a.length, b.length);
        for (int i = 0; i < minLen; ++i) {
            int aVal = a[i] & 0xFF;
            int bVal = b[i] & 0xFF;
            if (aVal < bVal) {
                return -1;
            }
            if (aVal > bVal) {
                return 1;
            }
        }
        if (a.length < b.length) {
            return -1;
        }
        if (a.length > b.length) {
            return 1;
        }
        return 0;
    }
    op20=>operation: public static String toHexString(byte[] data) {
        return data == null ?  : Hex.toHexString(data);
    }
    op21=>operation: public static byte[] fromHexString(String data) {
        if (data == null) {
            return EMPTY_BYTE_ARRAY;
        }
        if (data.startsWith(0x)) {
            data = data.substring(2);
        }
        if (data.length % 2 != 0) {
            data = 0 + data;
        }
        return Hex.decode(data);
    }
    e=>end: End

    st->op1
    op1->op2
    op2->op3
    op3->op4
    op4->op5
    op5->op6
    op6->op7
    op7->op8
    op8->op9
    op9->op10
    op10->op11
    op11->op12
    op12->op13
    op13->op14
    op14->op15
    op15->op16
    op16->op17
    op17->op18
    op18->op19
    op19->op20
    op20->op21
    op21->e
```
