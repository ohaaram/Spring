package kr.co.ch07t.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser4 is a Querydsl query type for User4
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser4 extends EntityPathBase<User4> {

    private static final long serialVersionUID = -901690768L;

    public static final QUser4 user4 = new QUser4("user4");

    public final StringPath addr = createString("addr");

    public final StringPath age = createString("age");

    public final StringPath gender = createString("gender");

    public final StringPath hp = createString("hp");

    public final StringPath name = createString("name");

    public final StringPath uid = createString("uid");

    public QUser4(String variable) {
        super(User4.class, forVariable(variable));
    }

    public QUser4(Path<? extends User4> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser4(PathMetadata metadata) {
        super(User4.class, metadata);
    }

}

