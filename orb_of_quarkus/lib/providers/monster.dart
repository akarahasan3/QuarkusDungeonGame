import 'package:flutter/foundation.dart';

import 'dungeon.dart';
import 'weapon.dart';

class Monster {
  final int id;
  final String name;
  final int health;
  final int damage;
  final Weapon weapon;
  final Dungeon dungeon;

  Monster({
    @required this.id,
    @required this.name,
    @required this.health,
    @required this.damage,
    @required this.weapon,
    @required this.dungeon,
  });
}
