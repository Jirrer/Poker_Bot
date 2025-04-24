package poker;

import java.util.Arrays;

class PreFlopValue {
    int[] values;

    public PreFlopValue(int card_1, int card_2, int suited) {
        this.values = new int[]{card_1, card_2, suited};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreFlopValue)) return false;
        PreFlopValue other = (PreFlopValue) o;
        return Arrays.equals(this.values, other.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}

