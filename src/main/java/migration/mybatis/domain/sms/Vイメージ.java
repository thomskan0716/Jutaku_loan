package migration.mybatis.domain.sms;

import java.io.Serializable;
import java.nio.file.Path;

import lombok.Data;

@Data
public class Vイメージ implements Serializable {

    private Path path;

    private Fax受信振分 faxJyushinfuriwake;

    private 申込徴求資料 moushikomiChokyuShiryo;

    private String imagePath;

}
