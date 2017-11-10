require_relative '../codes/extend_object_class'
require 'test/unit'

#
class TestExtendObjectClass < Test::Unit::TestCase
  def test_must_be_diferent_objects
    o = Object.new
    assert_not_equal(o, o.deep_copy)
  end

  def test_must_not_changed
    o = [{ a: 0 }]
    x = o.deep_copy
    x[0][:a] = 5
    assert_not_equal(o[0][:a], x[0][:a])
  end
end
