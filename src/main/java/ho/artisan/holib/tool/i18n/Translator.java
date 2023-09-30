package ho.artisan.holib.tool.i18n;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.TranslatableTextContent;

public class Translator {
    private final FabricLanguageProvider.TranslationBuilder builder;

    public Translator(FabricLanguageProvider.TranslationBuilder builder) {
        this.builder = builder;
    }

    public void gen(Item item) {
        builder.add(item, beautify(item.toString()));
    }

    public void gen(Item... items) {
        for (Item item : items) {
            gen(item);
        }
    }

    public void gen(ItemGroup itemGroup, String name) {
        if (itemGroup.getDisplayName().getContent() instanceof TranslatableTextContent content)
            builder.add(content.getKey(), name);
    }

    public String beautify(String name) {
        String[] str1 = name.split("_");
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < str1.length; i++) {
            str1[i] = str1[i].substring(0, 1).toUpperCase() + str1[i].substring(1);
            if (i == str1.length - 1)
                str2.append(str1[i]);
            else
                str2.append(str1[i]).append(" ");
        }
        return str2.toString();
    }
}
