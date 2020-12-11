package github.lightningcreations.lclib;

import java.util.Objects;

public final class VersionRange {
	private final Version start;
	private final Version end;
	public static final VersionRange ALL = new VersionRange(Version.valueOf(1,0),Version.valueOf(256,255));
	private VersionRange(/*const*/ Version start,/*const*/ Version end) {
		this.start = start;
		this.end = end;
	}

	public static VersionRange range(Version start,Version end){
	    return new VersionRange(Objects.requireNonNull(start),Objects.requireNonNull(end));
    }

	public static /*const*/ VersionRange fromString(/*const*/ String str) {
		if(str.equals("*"))
			return ALL;
		String[] split = str.split("-");
		return new VersionRange(Version.valueOf(split[0]),Version.valueOf(split[1]));
	}
	public boolean isWithin(/*const */Version v)/*const*/ {
		return start.compareTo(v)<=0&&end.compareTo(v)>0;
	}
	public /*const*/ Version getStart()/*const*/{
		return start;
	}
	public /*const*/ Version getEnd()/*const*/{
		return end;
	}
	public /*const*/ String toString()/*const*/{
		return start+"-"+end;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionRange that = (VersionRange) o;
        return start.equals(that.start) &&
                end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
