package kr.co.Dal.store.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LikeVO {
    public int userId;          // 회원번호
    public int prodId;          // 상품번호
    public int bardId;          // 게시글id
}
