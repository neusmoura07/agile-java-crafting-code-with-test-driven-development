public class Name {
    private String value;

    public Name(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        Name that = (Name) obj;
        return this.value.equals(that.value);
    }
}
