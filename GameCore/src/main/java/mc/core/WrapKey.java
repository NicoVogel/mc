package mc.core;

/**
 * this key is needed to enable a double key access for the elements map. only
 * one of the properties need to match, this is achieved by the equals method.
 */
public class WrapKey<T> {
    public Class<T> clazz;
    public Tag tag;

    public WrapKey(Class<T> clazz) {
        this(clazz, null);
    }

    public WrapKey(Tag tag) {
        this(null, tag);
    }

    /* package */ WrapKey(Class<T> clazz, Tag tag) {
        this.tag = tag;
        this.clazz = clazz;
    }

    public boolean isClass() {
        return this.clazz != null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WrapKey<?> == false)
            return false;
        WrapKey<?> obj = (WrapKey<?>) o;
        return this.clazz.equals(obj.clazz) || this.tag.equals(obj.tag);
    }
}