package migration.mybatis.domain.amigo_0149_c;

import java.io.Serializable;

import lombok.Data;

@Data
public class VEntryData implements Serializable {

    VEntryMain vEntryMain;

    VEntryLoan vEntryLoan;

    VEntryEtc vEntryEtc;

    VWebdata vWebdata;

    VEntryOffice vEntryOffice;

    VEntrySub vEntrySub;

}
