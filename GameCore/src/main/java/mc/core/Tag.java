package mc.core;

import lombok.Getter;

/**
 * Tag
 */
@Getter
public class Tag {

    private String tag;
    private int hash;

    public Tag(String tag) {
        setTag(tag);
    }

    public void setTag(String tag) {
        this.tag = tag;
        this.hash = tag.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof String) {
            String str = (String) o;
            return str.equals(this.tag);
        }
        if (o instanceof Tag == false) {
            return false;
        }
        Tag tag = (Tag) o;
        return this.hash == tag.hash;
    }
}