package com.pvptowerdefense.server.spring.cards;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Card.
 */
@Entity
@Table(name = "cards")
public class Card {
	@Id
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "COST", nullable = false)
	private int cost;

	@Column(name = "DAMAGE", nullable = false)
	private int damage;

	@Column(name = "HIT_POINTS", nullable = false)
	private int hitPoints;

	@Column(name = "SPEED", nullable = false)
	private int speed;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "CARD_RANGE", nullable = false)
	private int range;

	private Card(){

	}
	
	/**
	 * Instantiates a new Card.
	 *
	 * @param name        the name
	 * @param description the description
	 * @param cost        the cost
	 * @param damage      the damage
	 * @param hitPoints   the hit points
	 * @param speed       the speed
	 * @param type        the type
	 * @param range       the range
	 */
	public Card(String name, String description, int cost, int damage,
	            int hitPoints, int speed, String type, int range) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.damage = damage;
		this.hitPoints = hitPoints;
		this.speed = speed;
		setType(type);
		this.range = range;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets cost.
	 *
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Gets damage.
	 *
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets damage.
	 *
	 * @param damage the damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Gets hit points.
	 *
	 * @return the hit points
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Gets speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 * <p>
	 * TODO - verify it matches enum with following fields: unit, spell
	 *
	 * @param type the type
	 */
	private void setType(String type) {
		if (validType(type)) {
			this.type = type;
		}
		else {
			throw new IllegalArgumentException(String.format("The inputted " +
					"type, %s is invalid. \n Correct usage<%s>", type,
					getValidTypes()));
		}
	}

	/**
	 * Gets range.
	 *
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	private boolean validType(String type) {
		return CardType.SPELL.typeEquals(type) || CardType.UNIT.typeEquals(type);
	}

	private String getValidTypes() {
		StringBuilder types = new StringBuilder();

		for (CardType cardType : CardType.values()) {
			types.append(cardType.getType()).append("|");
		}

		types.deleteCharAt(types.length() - 1);

		return types.toString();
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("name", getName())
				.append("description", getDescription())
				.append("cost", getCost())
				.append("damage", getDamage())
				.append("hit points", getHitPoints())
				.append("speed", getSpeed())
				.append("type", getType())
				.append("range", getRange())
				.toString();
	}
}
