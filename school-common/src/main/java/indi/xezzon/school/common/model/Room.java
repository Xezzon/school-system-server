package indi.xezzon.school.common.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.text.MessageFormat;

/**
 * @author xezzon
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Room extends BaseEntity {
    private Long id;

    /**
     * 校区 dict.campus
     */
    private String campus;

    /**
     * 教学楼 dict.building
     */
    private String building;

    /**
     * 门牌号
     */
    private String doorplate;

    /**
     * 可容纳人数
     */
    private Integer containment;

    /**
     * 教室类型 dict.room_type
     */
    private Integer type;

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1}-{2}", this.campus, this.building, this.doorplate);
    }

    private static final long serialVersionUID = 1L;
}
