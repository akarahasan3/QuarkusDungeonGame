import 'package:flutter/foundation.dart';

import 'gamemap.dart';
import 'weapon.dart';

class Player {
  final int id;
  final String name;
  final int health;
  final int damage;
  final int healingPotion;
  final Weapon weapon;
  final GameMap map;

  Player({
    @required this.id,
    @required this.name,
    @required this.health,
    @required this.damage,
    @required this.healingPotion,
    @required this.weapon,
    @required this.map,
  });
}
