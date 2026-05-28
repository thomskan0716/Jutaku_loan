package migration.mybatis.domain.amigo_0149_c;

import java.io.Serializable;

import lombok.Data;

@Data
public class VJudgeMain extends TJudgeMain implements Serializable {

    private String requestId;

}
