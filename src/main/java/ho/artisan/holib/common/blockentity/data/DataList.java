package ho.artisan.holib.common.blockentity.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;

import java.util.ArrayList;
import java.util.List;

public class DataList {
    private final List<Integer> valueList;
    private final List<String> idList;

    private final PropertyDelegate delegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return valueList.get(index);
        }

        @Override
        public void set(int index, int value) {
            valueList.set(index, value);
        }

        @Override
        public int size() {
            return valueList.size();
        }
    };

    public DataList(int size) {
        this.valueList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.valueList.add(0);
        }
        this.idList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.idList.add("None");
        }
    }

    public Value create(int index, int defaultValue, String id) {
        valueList.set(index, defaultValue);
        idList.set(index, id);
        return new Value(index);
    }

    public Value create(int index, String id) {
        valueList.set(index, 0);
        idList.set(index, id);
        return new Value(index);
    }

    public void write(NbtCompound nbt) {
        for (int i = 0; i < valueList.size(); i++) {
            nbt.putInt(idList.get(i), valueList.get(i));
        }
    }

    public void read(NbtCompound nbt) {
        for (int i = 0; i < idList.size(); i++) {
            valueList.set(i, nbt.getInt(idList.get(i)));
        }
    }

    public class Value {
        private final int index;

        public Value(int index) {
            this.index = index;
        }

        public int get() {
            return valueList.get(index);
        }

        public void set(int value) {
            valueList.set(index, value);
        }

        public void plus(int value) {
            set(get() + value);
        }

        public void minus(int value) {
            set(get() - value);
        }

        public void times(int value) {
            set(get() * value);
        }

        public void inc() {
            plus(1);
        }

        public void dec() {
            minus(1);
        }
    }

    public PropertyDelegate delegate() {
        return delegate;
    }
}
