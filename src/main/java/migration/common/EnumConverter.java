package migration.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumConverter {
    private static final Logger logger = LoggerFactory.getLogger(EnumConverter.class);

    /**
     * 指定されたEnumクラスから、コードに一致する値を返す。
     *
     * @param enumClass Enumのクラス
     * @param itemName  項目名（ログ出力用）
     * @param code      コード値
     * @param <T>       ConvertibleEnumを実装したEnum型
     * @return 一致するEnumの値、またはnull（一致しない場合）
     */
    public static <T extends Enum<T> & ConvertibleEnum> T fromCode(Class<T> enumClass, String itemName, String code) {
        if (code == null) return null;

        for (T value : enumClass.getEnumConstants()) {
            if (code.equals(value.getOldCode())) {
                return value;
            }
        }

        logger.warn("対象なし: 項目={} / コード値={} / Enum={} 変換不可", itemName, code, enumClass.getSimpleName());
        return null;
    }
}
