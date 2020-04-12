package com.bluesky.middleplatform.commons.db;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * HQL/SQL增删改查的条件 *
 *
 * @param <K>
 * @param <V>
 * @author ElwinHe
 */

@Component(value = "Condition")
@Scope(value = "prototype")
public class Condition<K, V> extends HashMap<K, V> {
}
