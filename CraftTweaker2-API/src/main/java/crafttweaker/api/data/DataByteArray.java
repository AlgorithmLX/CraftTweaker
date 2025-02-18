package crafttweaker.api.data;

import java.util.*;

/**
 * Contains a byte array.
 *
 * @author Stan Hebben
 */
public class DataByteArray implements IData {
    
    private final byte[] data;
    private final boolean immutable;
    
    public DataByteArray(byte[] data, boolean immutable) {
        this.data = data;
        this.immutable = immutable;
    }
    
    @Override
    public IData add(IData other) {
        byte[] otherData = other.asByteArray();
        byte[] result = Arrays.copyOf(data, data.length + otherData.length);
        System.arraycopy(otherData, 0, result, data.length, otherData.length);
        return new DataByteArray(result, immutable);
    }
    
    @Override
    public IData sub(IData other) {
        throw new UnsupportedOperationException("Cannot subtract from a byte array");
    }
    
    @Override
    public IData mul(IData other) {
        throw new UnsupportedOperationException("Cannot multiply with an array");
    }
    
    @Override
    public IData div(IData other) {
        throw new UnsupportedOperationException("Cannot divide an array");
    }
    
    @Override
    public IData mod(IData other) {
        throw new UnsupportedOperationException("Cannot perform modulo on an array");
    }
    
    @Override
    public IData and(IData other) {
        throw new UnsupportedOperationException("Arrays don't support bitwise operations");
    }
    
    @Override
    public IData or(IData other) {
        throw new UnsupportedOperationException("Arrays don't support bitwise operations");
    }
    
    @Override
    public IData xor(IData other) {
        throw new UnsupportedOperationException("Arrays don't support bitwise operations");
    }
    
    @Override
    public IData neg() {
        throw new UnsupportedOperationException("Cannot negate arrays");
    }
    
    @Override
    public IData not() {
        throw new UnsupportedOperationException("Arrays don't support bitwise operations");
    }
    
    @Override
    public boolean asBool() {
        throw new IllegalDataException("Cannot cast an array to bool");
    }
    
    @Override
    public byte asByte() {
        throw new IllegalDataException("Cannot cast an array to byte");
    }
    
    @Override
    public short asShort() {
        throw new IllegalDataException("Cannot cast an array to short");
    }
    
    @Override
    public int asInt() {
        throw new IllegalDataException("Cannot cast an array to int");
    }
    
    @Override
    public long asLong() {
        throw new IllegalDataException("Cannot cast an array to long");
    }
    
    @Override
    public float asFloat() {
        throw new IllegalDataException("Cannot cast an array to float");
    }
    
    @Override
    public double asDouble() {
        throw new IllegalDataException("Cannot cast an array to double");
    }
    
    @Override
    public String asString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        boolean first = true;
        for(byte value : data) {
            if(first)
                first = false;
            else
                result.append(", ");
            
            result.append(value);
        }
        result.append("] as byte[]");
        return result.toString();
    }
    
    @Override
    public List<IData> asList() {
        List<IData> result = new ArrayList<>();
        for(byte value : data) {
            result.add(new DataByte(value));
        }
        return result;
    }
    
    @Override
    public Map<String, IData> asMap() {
        return null;
    }
    
    @Override
    public byte[] asByteArray() {
        return data;
    }
    
    @Override
    public int[] asIntArray() {
        int[] result = new int[data.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = data[i];
        }
        return result;
    }
    
    @Override
    public IData getAt(int i) {
        return new DataByte(data[i]);
    }
    
    @Override
    public void setAt(int i, IData value) {
        if(immutable) {
            throw new UnsupportedOperationException("Cannot modify this byte array");
        } else {
            data[i] = value.asByte();
        }
    }
    
    @Override
    public IData memberGet(String name) {
        throw new UnsupportedOperationException("byte[] doesn't have members");
    }
    
    @Override
    public void memberSet(String name, IData data) {
        throw new UnsupportedOperationException("cannot set byte[] members");
    }
    
    @Override
    public int length() {
        return data.length;
    }
    
    @Override
    public boolean contains(IData data) {
        return equals(data);
    }
    
    @Override
    public int compareTo(IData data) {
        throw new UnsupportedOperationException("Cannot compare arrays");
    }
    
    @Override
    public boolean equals(IData data) {
        return Arrays.equals(this.data, data.asByteArray());
    }
    
    @Override
    public IData immutable() {
        if(immutable) {
            return this;
        } else {
            return new DataByteArray(Arrays.copyOf(this.data, this.data.length), true);
        }
    }
    
    @Override
    public IData update(IData data) {
        return data;
    }
    
    @Override
    public <T> T convert(IDataConverter<T> converter) {
        return converter.fromByteArray(this.data);
    }
    
    @Override
    public String toString() {
        return asString() + " as byte[]";
    }
}
